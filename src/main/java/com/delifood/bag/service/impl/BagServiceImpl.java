package com.delifood.bag.service.impl;

import com.delifood.bag.enumeration.PaymentType;
import com.delifood.bag.model.Bag;
import com.delifood.bag.model.Item;
import com.delifood.bag.model.Restaurant;
import com.delifood.bag.repository.BagRepository;
import com.delifood.bag.repository.ItemRepository;
import com.delifood.bag.repository.ProductRepository;
import com.delifood.bag.resource.dto.ItemDTO;
import com.delifood.bag.service.BagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {

    private final BagRepository bagRepository;

    private final ProductRepository productRepository;

    private final ItemRepository itemRepository;

    @Override
    public Item addItem(ItemDTO itemDTO) {
        Bag retrievedBag = seeBag(itemDTO.getBagId());

        if(retrievedBag.getClosed()) {
            throw new RuntimeException("This bag is already closed!");
        }

        Item newItem = Item.builder()
                .product(productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(
                            () -> {
                                throw new RuntimeException("This product do not exist.");
                            }
                        )
                )
                .quantity(itemDTO.getQuantity())
                .bag(retrievedBag)
        .build();

        List<Item> items = retrievedBag.getItems();

        if(items.isEmpty()) {
            items.add(newItem);
        } else {
            Restaurant newItemsRestaurant = newItem.getProduct().getRestaurant();
            Restaurant bagsRestaurant = items.get(0).getProduct().getRestaurant();
            if(bagsRestaurant.equals(bagsRestaurant)) {
                items.add(newItem);
            } else {
                throw new RuntimeException("It is not possible to add an item from a restaurant " +
                        "that mismatch the existing items in your list. Empty your bag or " +
                        "close it before adding the new item.");
            }
        }

        List<Double> itemsPrice = new ArrayList<>();

        for(Item item : items) {
            Double unitValueTimesQuantity = item.getProduct().getUnitValue() * item.getQuantity();
            itemsPrice.add(unitValueTimesQuantity);
        }

        Double totalPrice = itemsPrice.stream()
                .mapToDouble(totalValueOfEachItem -> totalValueOfEachItem)
                .sum();

/*        Without using Java Stream API
        Double totalPrice = 0.0;

        for(Double price : itemsPrice) {
            totalPrice += price;
        }
 */

        retrievedBag.setTotalPrice(totalPrice);

        bagRepository.save(retrievedBag);

        return newItem;
    }

    @Override
    public Bag closeBag(Long id, int paymentTypeNumber) {
        Bag bag = seeBag(id);

        if(bag.getItems().isEmpty()) {
            throw new RuntimeException("Add at least one item before closing the bag!");
        }

        PaymentType paymentType = paymentTypeNumber == 0 ? PaymentType.CASH : PaymentType.MACHINE;

        bag.setPaymentType(paymentType);
        bag.setClosed(true);
        bag = bagRepository.save(bag);

        return bag;
    }

    @Override
    public Bag seeBag(Long id) {
        return bagRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Bag cannot be found!");
                });
    }
}
