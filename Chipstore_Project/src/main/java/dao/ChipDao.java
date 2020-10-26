package dao;

import pojo.Chip;

import java.util.List;

public interface ChipDao {
    public int addChip(Chip chip);
    public int deleteChipById(Integer id);
    public int updateChip(Chip chip);
    public Chip queryChipById(Integer id);
    public List<Chip> queryChips();
    public Integer queryForPageTotalCount();
    public List<Chip> queryForPageItems(Integer begin, Integer pageSize);
    public Integer queryForTotalCountByPrice(Integer min, Integer max);
    public List<Chip> queryForItemsByPrice(Integer begin, Integer size,Integer min, Integer max);
}
