package cc.eslink.fundanalyze.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 阶段涨幅明细
 *
 * @author zyk
 */
@Table(name = "fund_stage")
@Data
@NoArgsConstructor
public class FundStage {

    private static final long serialVersionUID = 1L;

    //=================================================

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 基金代码 */
    private String fundCode;

    /** 周期类型：0-1周；1-1月；2-3月；3-6月；4-今年；5-1年；6-2年；7-3年；8-成立以来 */
    private Integer stageType;

    /** 统计日期 */
    private String calcDate;

    /** 阶段涨幅 */
    private Double stageRate;

    /**  */
    private Double avgRate;

    /** 沪深300 */
    private Double hushen300;

    /** 同类排名 */
    private Integer sort;

    /*总共*/
    private Integer total;

    /** 四分位排名：优秀、良好、一般、不佳 */
    private String ranking;

    /*得分：优秀4、良好3、一般2、不佳1*/
    private Integer score;

    public FundStage(String fundCode, String calcDate) {
        this.fundCode = fundCode;
        this.calcDate = calcDate;
    }

    @Override
    public String toString() {
        return "FundStage{" +
                "id=" + id +
                ", fundCode='" + fundCode + '\'' +
                ", stageType=" + stageType +
                ", calcDate='" + calcDate + '\'' +
                ", stageRate=" + stageRate +
                ", avgRate=" + avgRate +
                ", hushen300=" + hushen300 +
                ", sort=" + sort +
                ", total=" + total +
                ", ranking='" + ranking + '\'' +
                ", score=" + score +
                '}';
    }
}
