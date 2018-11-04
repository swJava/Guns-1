package com.stylefeng.guns.modular.questionMGR.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.questionMGR.service.IQuestionService;
import com.stylefeng.guns.modular.system.dao.QuestionMapper;
import com.stylefeng.guns.modular.system.model.Question;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入学诊断试题库 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-11-04
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
