let table = layui.table;
let laydate = layui.laydate;

laydate.render({
    elem: '#createTimeRange',
    range: true
})

var tableIns = table.render({
    elem: '#accountList',
    url: '/account/list',
    page:true,
    parseData: function (res){
        return {
            "code": res.code,
            "msg": res.msg,
            "count": res.data.count,
            "data": res.data.records
        };
    },
    cols:[[
        {
            field: 'username',
            title: '用户名'
        },
        {
            field: 'realName',
            title: '真实姓名'
        },
        {
            field: 'roleName',
            title: '角色名称'
        },
        {
            field: 'sex',
            title: '性别'
        },
        {
            field: 'createTime',
            title: '创建时间'
        },
        {
            title: '操作',
            toolbar: '#barDemo'
        }
    ]]
});

function query(){
    tableIns.reload({
        where: {
            realName: $("#realName").val(),
            email: $("email").val(),
            createTimeRange: $("createTimeRange").val()
        },page: {
            curr: 1
        }
    });
}

function intoAdd(){
    openLayer("/account/toAdd", "新增账号");
    let form = layui.form;
    form.render();
    mySubmit('addSubmit', "POST");
}

//工具条事件
table.on('tool(tests)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
    let accountId = data.accountId;
    if(layEvent === 'detail'){ //查看
        //do somehing
        openLayer('/account/toDetail/'+accountId, '账号详情')
    } else if(layEvent === 'del'){ //删除
        layer.confirm('真的删除行么', function(index){
            layer.close(index);
            myDelete('/account/delete/'+accountId)
        });
    } else if(layEvent === 'edit'){ //编辑
        //do something
        openLayer('/account/toUpdate/'+accountId, '编辑账号')
        layui.form.render();
        mySubmit('updateSubmits', 'PUT')
    }
});