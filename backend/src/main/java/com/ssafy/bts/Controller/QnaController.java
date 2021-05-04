package com.ssafy.bts.Controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/qna")
@RequiredArgsConstructor
public class QnaController {
}
