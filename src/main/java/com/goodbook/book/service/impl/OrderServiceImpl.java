package com.goodbook.book.service.impl;

import com.goodbook.book.dao.BookDao;
import com.goodbook.book.dao.OrderBookDao;
import com.goodbook.book.dao.OrderDao;
import com.goodbook.book.model.*;
import com.goodbook.book.service.interfaces.OrderService;
import com.goodbook.book.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderBookDao orderBookDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    UserService userService;

    @Autowired
    CardDto cardDto;

    @Override
    public Map create() {
        Map<String, Object> map = new HashMap<>();
        try {
            UserDto user = userService.getUser().get();

            if (cardDto.getCard().size() == 0 ) {
                map.put("success", false);
                return map;
            }

            OrderDto orderDto = orderDao.save(new OrderDto(user));

            for(Map.Entry<Integer, Integer> cardItem : cardDto.getCard().entrySet()) {
                bookDao.findById(cardItem.getKey()).ifPresent(
                        bookDto -> {
                            OrderBookDto orderBookDto = new OrderBookDto(bookDto, orderDto, cardItem.getValue());
                            orderDto.plusSumToOrder(bookDto.getPrice() * cardItem.getValue());
                            orderBookDao.save(orderBookDto);
                        }
                );
            }
            cardDto.clearCard();
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
        }
        return map;
    }

    @Override
    public Map delete(long id) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserDto user = userService.getUser().get();
            Optional<OrderDto> orderDto = orderDao.findByIdAndUser(id, user);
            orderDao.findById(id).ifPresent(order -> orderDao.delete(order));
            map.put("success", orderDto.isPresent());
        } catch (Exception e) {
            map.put("success", false);
        }
        return map;
    }

    @Override
    public Map get(long id) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserDto user = userService.getUser().get();
            Optional<OrderDto> orderDto = orderDao.findByIdAndUser(id, user);
            orderDao.findById(id).ifPresent(order -> {
                map.put("order", order);
            });
            map.put("success", orderDto.isPresent());
        } catch (Exception e) {
            map.put("success", false);
        }

        return map;
    }

    @Override
    public Map getAll() {
        Map<String, Object> map = new HashMap<>();
        try {
            UserDto user = userService.getUser().get();
            List<OrderDto> orders = orderDao.findAllByUser(user);
            map.put("Orders", orders);
        } catch (Exception e) {
            map.put("success", false);
        }
        return map;
    }








    @Override
    public Map getAllOrderByUser(UserDto user) {
        Map<String, Object> map = new HashMap<>();

        return map;    }

    @Override
    public Map getOrderByUserAndId(UserDto user, long id) {
        Map<String, Object> map = new HashMap<>();

        return map;    }

    @Override
    public Map setStatus(UserDto user, long id, String status) {
        Map<String, Object> map = new HashMap<>();

        return map;    }
}
