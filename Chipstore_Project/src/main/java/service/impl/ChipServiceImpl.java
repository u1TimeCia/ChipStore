package service.impl;

import dao.ChipDao;
import dao.impl.ChipDaoImpl;
import pojo.Chip;
import pojo.Page;
import pojo.HomePage;
import service.ChipService;

import java.util.List;

public class ChipServiceImpl implements ChipService {

    ChipDao chipDao = new ChipDaoImpl();

    @Override
    public void addChip(Chip chip) {
        chipDao.addChip(chip);
    }

    @Override
    public void deleteChipById(Integer id) {
        chipDao.deleteChipById(id);
    }

    @Override
    public void updateChip(Chip chip) {
        chipDao.updateChip(chip);
    }

    @Override
    public Chip queryChipById(Integer id) {
        return chipDao.queryChipById(id);
    }

    @Override
    public List<Chip> queryChips() {
        return chipDao.queryChips();
    }

    @Override
    public Page<Chip> page(Integer pageNo, Integer pageSize) {
        Page<Chip> page = new Page<Chip>();
        page.setPageSize(pageSize);
        Integer pageTotalCount = chipDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount % pageSize > 0 ? pageTotalCount / pageSize + 1 : pageTotalCount / pageSize;
        page.setPageTotal(pageTotal);
        if (pageNo < 1) {
            pageNo = 1;
        } else if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        page.setPageNo(pageNo);
        Integer begin = (pageNo - 1) * pageSize;
        List<Chip> items = chipDao.queryForPageItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Chip> pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max) {
        Page<Chip> page = new Page<Chip>();
        page.setPageSize(pageSize);
        Integer pageTotalCount = chipDao.queryForTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount % pageSize > 0 ? pageTotalCount / pageSize + 1 : pageTotalCount / pageSize;
        page.setPageTotal(pageTotal);
        if (pageNo < 1) {
            pageNo = 1;
        } else if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        page.setPageNo(pageNo);
        Integer begin = (pageNo - 1) * pageSize;
        List<Chip> items = chipDao.queryForItemsByPrice(begin, pageSize, min, max);
        page.setItems(items);
        return page;
    }
}
