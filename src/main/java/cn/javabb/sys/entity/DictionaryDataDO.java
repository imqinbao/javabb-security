package cn.javabb.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 */
@ApiModel(description = "字典项")
@TableName("sys_dictionary_data")
public class DictionaryDataDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典项id")
    @TableId(value = "dict_data_id", type = IdType.AUTO)
    private Integer dictDataId;

    @ApiModelProperty(value = "字典id")
    private Integer dictId;

    @ApiModelProperty(value = "字典项标识")
    private String dictDataCode;

    @ApiModelProperty(value = "字典项名称")
    private String dictDataName;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除,0否,1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "字典代码")
    @TableField(exist = false)
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    @TableField(exist = false)
    private String dictName;

    public Integer getDictDataId() {
        return dictDataId;
    }

    public void setDictDataId(Integer dictDataId) {
        this.dictDataId = dictDataId;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDictDataCode() {
        return dictDataCode;
    }

    public void setDictDataCode(String dictDataCode) {
        this.dictDataCode = dictDataCode;
    }

    public String getDictDataName() {
        return dictDataName;
    }

    public void setDictDataName(String dictDataName) {
        this.dictDataName = dictDataName;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    @Override
    public String toString() {
        return "DictionaryData{" +
                ", dictDataId=" + dictDataId +
                ", dictId=" + dictId +
                ", dictDataCode=" + dictDataCode +
                ", dictDataName=" + dictDataName +
                ", sortNumber=" + sortNumber +
                ", comments=" + comments +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                ", dictCode=" + dictCode +
                ", dictName=" + dictName +
                "}";
    }

}
