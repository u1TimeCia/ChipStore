package dao.impl;

import dao.ChipDao;
import pojo.Chip;

import java.util.List;

public class ChipDaoImpl extends BaseDao implements ChipDao {

    @Override
    public int addChip(Chip chip) {
        String sql = "insert into t_chip (name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql, chip.getName(), chip.getAuthor(), chip.getPrice(), chip.getSales(), chip.getStock(), chip.getImgPath());
    }

    @Override
    public int deleteChipById(Integer id) {
       String sql = "delete from t_chip where id=?";
       return update(sql, id);
    }

    @Override
    public int updateChip(Chip chip) {
        String sql = "update t_chip set name=?,author=?,price = ?,sales=?,stock=?,img_path=? where id=?";
        return update(sql, chip.getName(), chip.getAuthor(), chip.getPrice(), chip.getSales(), chip.getStock(), chip.getImgPath(), chip.getId());
    }

    @Override
    public Chip queryChipById(Integer id) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_chip where id=?";
        return queryForOne(Chip.class, sql, id);
    }

    @Override
    public List<Chip> queryChips() {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_chip";
        return queryForList(Chip.class, sql);
    }

    /**
     * use type Number as data in MySQL is type long, Number can avoid overflow
     * @param
     * @return
     * @author Haoran Qi
     * @date
     */
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_chip";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Chip> queryForPageItems(Integer begin, Integer pageSize) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_chip limit ?,?";
        return queryForList(Chip.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForTotalCountByPrice(Integer min, Integer max) {
        String sql = "select count(*) from t_chip where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min,max);
        return count.intValue();
    }

    @Override
    public List<Chip> queryForItemsByPrice(Integer begin, Integer size, Integer min, Integer max) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_chip where price between ? and ? limit ?,? ";
        return queryForList(Chip.class, sql, min,max, begin,size);
    }
}
