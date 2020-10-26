package service;

import pojo.Chip;
import pojo.Page;
import pojo.HomePage;

import java.util.List;

public interface ChipService {

    public void addChip(Chip chip);

    public void deleteChipById(Integer id);

    public void updateChip(Chip chip);

    public Chip queryChipById(Integer id);

    public List<Chip> queryChips();

    Page<Chip> page(Integer pageNo, Integer pageSize);

    Page<Chip> pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max);


}
