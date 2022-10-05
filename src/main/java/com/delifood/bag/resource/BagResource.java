package com.delifood.bag.resource;

import com.delifood.bag.model.Item;
import com.delifood.bag.model.Bag;
import com.delifood.bag.resource.dto.ItemDTO;
import com.delifood.bag.service.BagService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "/delifood/bags")
@RestController
@RequestMapping(path = "/delifood/bags")
@RequiredArgsConstructor
public class BagResource {


    private final BagService bagService;

    @PostMapping
    public Item addItem(@RequestBody ItemDTO itemDTO) {
        return bagService.addItem(itemDTO);
    }

    @GetMapping(path = "/{id}")
    public Bag seeBag(@PathVariable("id") Long id) {
        return bagService.seeBag(id);
    }

    @PatchMapping(path = "/closeBag/{bagId}")
    public Bag closeBag(
            @PathVariable("bagId") Long bagId,
            @RequestParam("paymentType") int paymentType) {
        return bagService.closeBag(bagId, paymentType);
    }

}
