package com.delifood.bag.service.impl;

import com.delifood.bag.enumeration.PaymentType;
import com.delifood.bag.model.Bag;
import com.delifood.bag.model.Item;
import com.delifood.bag.repository.BagRepository;
import com.delifood.bag.resource.dto.ItemDTO;
import com.delifood.bag.service.BagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {

    private final BagRepository bagRepository;

    @Override
    public Item addItem(ItemDTO itemDTO) {
        return null;
    }

    @Override
    public Bag closeBag(Long id, int paymentTypeNumber) {
        Bag bag = seeBag(id);

        if(bag.getItems().isEmpty()) {
            throw new RuntimeException("Add at least one item before closing the bag");
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
