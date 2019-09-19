package com.gly.common.model.ucenter.ext;

import com.gly.common.model.ucenter.Menu;
import com.gly.common.model.course.ext.CategoryNode;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class MenuExt extends Menu {

    List<CategoryNode> children;
}
