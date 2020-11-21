package cn.cruder.reflection.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: cruder
 * @Date: 2020-11-21 15:48
 * @Description: description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResourceDataInformation {


    /**
     * 计价基期
     */
    private Date basePeriodPrice;

    /**
     * 上传时输入类型
     */
    private String type;

    /**
     * 版本号
     */
    private String versionNum;


    /**
     * 工程名称
     */
    private String projectName;

    /**
     * 项目编号
     */
    private String projectNumber;

    /**
     * 建设单位
     */
    private String construction;

    /**
     * 建设单位 性质(存放备注列信息)
     */
    private String constructionNature;

    /**
     * 设计单位
     */
    private String designUnit;

    /**
     * 设计单位 性质(存放备注列信息)
     */
    private String designUnitNature;

    /**
     * 勘察单位
     */
    private String surveyUnit;

    /**
     * 勘察单位 性质(存放备注列信息)
     */
    private String surveyUnitNature;

    /**
     * 招标代理
     */
    private String biddingAgency;

    /**
     * 招标代理 性质(存放备注列信息)
     */
    private String biddingAgencyNature;

    /**
     * 造价咨询
     */
    private String costConsultation;

    /**
     * 造价咨询 性质(存放备注列信息)
     */
    private String costConsultationNature;

    /**
     * 总包单位
     */
    private String mainContractor;


    /**
     * 总包单位 性质(存放备注列信息)
     */
    private String mainContractorNature;

    /**
     * 监理单位
     */
    private String supervisUnit;

    /**
     * 监理单位 性质(存放备注列信息)
     */
    private String supervisUnitNature;

    /**
     * 建设地点
     */
    private String constructionSite;

    /**
     * 土地性质
     */
    private String natureOfLand;

    /**
     * 资金来源
     */
    private String capitalSource;

    /**
     * 发包方式
     */
    private String contractWay;

    /**
     * 合同方式
     */
    private String formOfTreaty;

    /**
     * 项目投资
     */
    private BigDecimal projectInvestment;

    /**
     * 建安工程费
     */
    private BigDecimal civilAndErectionCost;

    /**
     * 占地面积
     */
    private BigDecimal floorSpace;

    /**
     * 总建筑面积
     */
    private BigDecimal totalFloorArea;

    /**
     * 地上面积
     */
    private BigDecimal groundArea;

    /**
     * 地下面积
     */
    private BigDecimal undergroundArea;

    /**
     * 建筑檐高
     */
    private BigDecimal highBuildingEaves;

    /**
     * 地基处理
     */
    private String foundationTreatment;

    /**
     * 基础埋深
     */
    private BigDecimal depth;

    /**
     * 基础类型
     */
    private String baseType;

    /**
     * 地下水位
     */
    private BigDecimal undergroundWaterLevel;

    /**
     * 基坑支护
     */
    private String foundationPit;

    /**
     * 结构类型
     */
    private String structureType;

    /**
     * 使用功能
     */
    private String useFunction;

    /**
     * 设防烈度
     */
    private String defensiveIntensity;

    /**
     * 节能标准
     */
    private BigDecimal energySavingStandard;

    /**
     * 质量标准
     */
    private String qualityStandard;

    /**
     * 质量奖项
     */
    private String qualityAward;

    /**
     * 人防面积
     */
    private BigDecimal civilAirDefenseArea;

    /**
     * 人防类型
     */
    private String civilAirDefenseType;

    /**
     * 平常用途
     */
    private String normalUse;

    /**
     * 战时用途
     */
    private String wartimePurposes;

    /**
     * 计划开工
     */
    private Date planConstruction;

    /**
     * 计划竣工
     */
    private Date planCompletion;

    /**
     * 计划工期
     */
    private BigDecimal planDuration;

    /**
     * 实际开工
     */
    private Date actualConstruction;

    /**
     * 实际竣工
     */
    private Date actualCompletion;

    /**
     * 实际工期
     */
    private BigDecimal actualDuration;

    /**
     * 外墙装修
     */
    private String exteriorWallDecoration;

    /**
     * 内装范围
     */
    private String interiorDecorationScope;

    /**
     * 内装标准
     */
    private String interiorDecorationStandard;

    /**
     * 门窗做法
     */
    private String doorsAndWindows;

    /**
     * 采暖方式
     */
    private String heatingMode;

    /**
     * 制冷方式
     */
    private String refrigerationMode;

    //suppliers

    /**
     * 单体指标  json数组
     * key 分别为
     * 单体名称
     * 单体面积
     * 地上层数
     * 地上面积
     * 地下层数
     * 地下面积
     */
    private String monosomeIndicator;

    /**
     * 供应商 json
     */
    private String suppliers;

    /**
     * 承包商 json
     */
    private String contractor;

}
