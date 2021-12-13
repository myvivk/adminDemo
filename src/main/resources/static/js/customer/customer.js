let table = layui.table;
var tableIns = table.render({
    elem: '#customerList',
    url: '/customer/list',
    page: true,
    parseData: function(res){
        return {
            "code": res.code,
            "msg": res.msg,
            "count": res.data.count,
            "data": res.data.records
        };
    },
    cols: [[ //表头
        {field: 'realName', title: '真实姓名'}
        ,{field: 'sex', title: '性别'}
        ,{field: 'age', title: '年龄'}
        ,{field: 'phone', title: '手机号码'}
        ,{field: 'createTime', title: '创建时间'}
        ,{title: '操作',toolbar:'#barDemo'}
    ]]
});

function query(){
    tableIns.reload({
        where: {
            realName: $("#realName").val(),
            phone: $("#phone").val()
        },
        page: {
            curr: 1
        }
    });
}

function toAdd(){
    $.get('/customer/toAdd', function (res){
        openLayer('/customer/toAdd', '新增客户')
        layui.form.render();
        mySubmit('addSubmit', 'POST')
    });
}

//工具条事件
table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
    let customerId = data.customerId;
    if(layEvent === 'detail'){ //查看
        //do somehing
        openLayer('/customer/toDetail/'+customerId, '客户详情')
    } else if(layEvent === 'del'){ //删除
        layer.confirm('真的删除行么', function(index){
            myDelete('/customer/delete/'+customerId)
        });
    } else if(layEvent === 'edit'){ //编辑
        //do something
        openLayer('/customer/toUpdate/'+customerId, '编辑客户')
        layui.form.render();
        mySubmit('updateSubmit', 'PUT')
    }
});
