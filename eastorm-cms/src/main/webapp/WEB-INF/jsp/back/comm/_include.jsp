<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/ext-4.2.1.883/examples/ux/statusbar/css/statusbar.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/ext-4.2.1.883/examples/writer/writer.css" />

<script type="text/javascript" src="<%=basePath %>js/ext-4.2.1.883/examples/shared/include-ext.js"></script>
<script type="text/javascript" src="<%=basePath %>js/ext-4.2.1.883/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/util.js"></script>
<script type="text/javascript" src="<%=basePath %>js/back/comm.js"></script>
<script>
Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', '<%=basePath %>js/ext-4.2.1.883/examples/ux');
var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
//Add the additional 'advanced' VTypes
Ext.apply(Ext.form.field.VTypes, {
    daterange: function(val, field) {
        var date = field.parseDate(val);

        if (!date) {
            return false;
        }
        if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
            var start = field.up('form').down('#' + field.startDateField);
            start.setMaxValue(date);
            start.validate();
            this.dateRangeMax = date;
        }
        else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
            var end = field.up('form').down('#' + field.endDateField);
            end.setMinValue(date);
            end.validate();
            this.dateRangeMin = date;
        }
        /*
         * Always return true since we're only using this vtype to set the
         * min/max allowed values (these are tested for after the vtype test)
         */
        return true;
    },

    daterangeText: '开始时间必须大于结束时间',

    password: function(val, field) {
        if (field.initialPassField) {
            var pwd = field.up('form').down('#' + field.initialPassField);
            return (val == pwd.getValue());
        }
        return true;
    },

    passwordText: '密码不一致'
});



</script>