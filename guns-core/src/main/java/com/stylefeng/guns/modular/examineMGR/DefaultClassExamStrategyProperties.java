package com.stylefeng.guns.modular.examineMGR;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/2 16:25
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "application.examine.strategy.default")
public class DefaultClassExamStrategyProperties {

    private Integer count = 0;

    private Integer duration = 0;

    private Integer fullCredit = 0;

    private BigDecimal selectRatio = new BigDecimal("0");

    private BigDecimal fillRatio = new BigDecimal("0");

    private BigDecimal subjectRatio = new BigDecimal("0");

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFullCredit() {
        return fullCredit;
    }

    public void setFullCredit(Integer fullCredit) {
        this.fullCredit = fullCredit;
    }

    public BigDecimal getSelectRatio() {
        return selectRatio;
    }

    public void setSelectRatio(BigDecimal selectRatio) {
        this.selectRatio = selectRatio;
    }

    public BigDecimal getFillRatio() {
        return fillRatio;
    }

    public void setFillRatio(BigDecimal fillRatio) {
        this.fillRatio = fillRatio;
    }

    public BigDecimal getSubjectRatio() {
        return subjectRatio;
    }

    public void setSubjectRatio(BigDecimal subjectRatio) {
        this.subjectRatio = subjectRatio;
    }
}
