package cc.eslink.fundanalyze.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 基金净值
 *
 * @author zyk
 */
@Table(name = "fund_net")
@Data
@NoArgsConstructor
public class FundNet {

    private static final long serialVersionUID = 1L;

    //=================================================

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 基金代码 */
    private String fundCode;

    /** 净值日期 */
    private String netDate;

    /** 单位净值 */
    private Double netValue;

    /** 累计净值 */
    private Double totalValue;

    /** 日增长率 */
    private Double dailyRate;

    /** 申购状态 */
    private String subStatus;

    /** 赎回状态 */
    private String redStatus;

    public FundNet(String fundCode) {
        this.fundCode = fundCode;
    }
}
