package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.bo.BizArbiterMapCoordinateBo;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @author kyc
 * @date 2024/2/28 18:57
 */
@Data
public class BatchSaveArbiterMapCoordinateDto {

    private String mapId;

    private List<BizArbiterMapCoordinateBo> detailList;

}
