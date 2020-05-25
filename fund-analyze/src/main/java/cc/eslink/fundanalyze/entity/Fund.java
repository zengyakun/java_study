package cc.eslink.fundanalyze.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


/**
 * 基金
 *
 * @author zyk
 */
@Table(name = "fund")
@Data
@NoArgsConstructor
public class Fund {

    private static final long serialVersionUID = 1L;

    //=================================================

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 基金代码 */
    private String fundCode;

    /** 基金名称 */
    private String fundName;

    /** 基金类型 */
    private String fundType;

    /** 发行日期 */
    private Timestamp issueDate = null;

    /** 资产规模 */
    private Double asset = null;

    /** 基金经理 */
    private String fundManager;

    public Fund(String fundCode, String fundName) {
        this.fundCode = fundCode;
        this.fundName = fundName;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "id=" + id +
                ", fundCode='" + fundCode + '\'' +
                ", fundName='" + fundName + '\'' +
                ", fundType='" + fundType + '\'' +
                ", issueDate=" + issueDate +
                ", asset=" + asset +
                ", fundManager='" + fundManager + '\'' +
                '}';
    }
}
