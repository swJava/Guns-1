/**
 * 试卷管理
 */
var PaperWizardReviewer = {
    Wizard: {
        id: 'wizard'
    }
};

$(function () {

    var form = $('#' + PaperWizardReviewer.Wizard.id);
    form.steps({
        headerTag: "h1",
        bodyTag: "fieldset",
        transitionEffect: "slideLeft",
        autoFocus: true,
        labels: {
            finish: "关闭", // 修改按钮得文本
            next: "下一题", // 下一题按钮的文本
            previous: "上一题", // 上一题按钮的文本
            loading: "Loading ..."
        }
    });
});
