package test;

import dao.ChipDao;
import dao.impl.ChipDaoImpl;
import org.junit.jupiter.api.Test;
import pojo.Chip;

import java.math.BigDecimal;
import java.util.List;

class ChipDaoTest {
    ChipDao chipDao = new ChipDaoImpl();

    @Test
    void addChip() {
        chipDao.addChip(new Chip(null,"HUD: Contro","9S",new BigDecimal(9999),110000,0,null));
    }

    @Test
    void deleteChipById() {

    }

    @Test
    void updateChip() {
        chipDao.updateChip(new Chip(21,"HUD: sound waves","9S",new BigDecimal(9999),110000,0,null));
    }

    @Test
    void queryChipById() {
        System.out.println(chipDao.queryChipById(21));
    }

    @Test
    void queryChips() {
        chipDao.queryChips().forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount(){
        System.out.println(chipDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems(){
        chipDao.queryForPageItems(1, 4).forEach(System.out::println);
    }

    @Test
    void queryForTotalCountByPrice() {
        System.out.println(chipDao.queryForTotalCountByPrice(10, 50));
    }

    @Test
    void queryForItemsByPrice() {
        chipDao.queryForItemsByPrice(1, 10,10,50).forEach(System.out::println);
    }
}