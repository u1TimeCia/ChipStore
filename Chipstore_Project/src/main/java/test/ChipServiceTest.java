package test;

import org.junit.jupiter.api.Test;
import pojo.Chip;
import pojo.Page;
import service.ChipService;
import service.impl.ChipServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ChipServiceTest {

    private ChipService chipService = new ChipServiceImpl();

    @Test
    void addChip() {
        chipService.addChip(new Chip(null,"HUD: Control","9S",new BigDecimal(1000),1000,0,null));
    }

    @Test
    void deleteChipById() {
        chipService.deleteChipById(22);
    }

    @Test
    void updateChip() {

    }

    @Test
    void queryChipById() {

    }

    @Test
    void queryChips() {
        chipService.queryChips().forEach(System.out::println);
    }

    @Test
    public void page(){
        System.out.println(chipService.page(1, 4));
    }
}