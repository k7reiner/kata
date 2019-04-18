package com.pencil.kata.rest;

import com.pencil.kata.service.RedPencilDeterminator;
import com.pencil.kata.repository.FauxProductRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedPencilController {

    private final RedPencilDeterminator redPencilDeterminator;
    private final FauxProductRepository fauxProductRepository;

    public RedPencilController(RedPencilDeterminator redPencilDeterminator, FauxProductRepository fauxProductRepository) {
        this.redPencilDeterminator = redPencilDeterminator;
        this.fauxProductRepository = fauxProductRepository;
    }

    @RequestMapping("/redpencil")
    public String isRedPencilItem(@RequestParam(value="id") String id ) {
        return "id: " + id + "    isRedPencilQualified:  " + redPencilDeterminator.isQualified(id);
    }
}
