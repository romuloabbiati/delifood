package com.delifood.bag.service;

import com.delifood.bag.model.Bag;
import com.delifood.bag.model.Item;
import com.delifood.bag.resource.dto.ItemDTO;

public interface BagService {

    Item addItem(ItemDTO itemDTO);
    Bag closeBag(Long id, int paymentType);
    Bag seeBag(Long id);

}
