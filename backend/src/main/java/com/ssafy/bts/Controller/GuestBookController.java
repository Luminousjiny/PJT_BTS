package com.ssafy.bts.Controller;

import com.ssafy.bts.Service.GuestBookService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/guestbook")
@RequiredArgsConstructor
public class GuestBookController {
    private final GuestBookService guestBookService;



}
