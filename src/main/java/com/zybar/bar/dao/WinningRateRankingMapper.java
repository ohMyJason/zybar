package com.zybar.bar.dao;

import com.zybar.bar.model.WinningRateRanking;

import java.util.List;

public interface WinningRateRankingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_winning_rate_ranking
     *
     * @mbggenerated Sat Aug 17 15:06:34 CST 2019
     */
    int deleteByPrimaryKey(Integer winningRateRankingId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_winning_rate_ranking
     *
     * @mbggenerated Sat Aug 17 15:06:34 CST 2019
     */
    int insert(WinningRateRanking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_winning_rate_ranking
     *
     * @mbggenerated Sat Aug 17 15:06:34 CST 2019
     */
    int insertSelective(WinningRateRanking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_winning_rate_ranking
     *
     * @mbggenerated Sat Aug 17 15:06:34 CST 2019
     */
    WinningRateRanking selectByPrimaryKey(Integer winningRateRankingId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_winning_rate_ranking
     *
     * @mbggenerated Sat Aug 17 15:06:34 CST 2019
     */
    int updateByPrimaryKeySelective(WinningRateRanking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_winning_rate_ranking
     *
     * @mbggenerated Sat Aug 17 15:06:34 CST 2019
     */
    int updateByPrimaryKey(WinningRateRanking record);

    List<WinningRateRanking> getAllWinningRateRanking();
}