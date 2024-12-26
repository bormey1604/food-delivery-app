package com.techgirl.order_service.utils;

import com.techgirl.order_service.model.MenuEntity;
import com.techgirl.order_service.model.MenuResponse;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class MenuItemMapper {

    public static List<MenuResponse> toResponse(List<MenuEntity> menuItems) {
        if (menuItems == null) {
            return null;
        }

        List<MenuResponse> responseList = new ArrayList<>();
        for (MenuEntity menuItem : menuItems) {
            responseList.add(new MenuResponse(menuItem.getId(), menuItem.getName(), menuItem.getPrice()));
        }

        return responseList;
    }

    public static MenuResponse toResponse(MenuEntity menuItem) {
        if (menuItem == null) {
            return null;
        }

        return new MenuResponse(menuItem.getId(), menuItem.getName(), menuItem.getPrice());
    }

}