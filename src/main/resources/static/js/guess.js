const count = document.getElementById("count");

const layer = layui.layer;

// 角色猜测
function guess() {
    let name = document.getElementsByName('guess-input')[0].value;
    document.getElementById('guess-input').value = '';
    $.ajax({
        url: '/character/guess',
        method: 'POST',
        data: {name: name},
        success:function(data) {
            if (data.flag !== "error") { // 角色存在
                // 将剩余猜测次数-1
                count.innerHTML = parseInt(count.innerHTML) - 1;
                // 将搜索框中的文字清除
                let th = document.getElementById('guess-th');
                // 将表头显现
                if (th.hidden === true) {
                    th.removeAttribute('hidden');
                }
                let help = document.getElementById("guess-help")
                if (help.hidden === true) {
                    help.removeAttribute('hidden');
                }
                let table = document.getElementById('guess-table');
                let row = table.insertRow();
                data.result.forEach(function (key) {
                    if (key) {
                        row.insertCell().innerHTML = "<img src='./img/yes.png' alt='√'>"
                    } else {
                        row.insertCell().innerHTML = "<img src='./img/no.png' alt='×'>"
                    }
                })
                row.insertCell().innerHTML = "<a href='javaScript:void(0)' class='text-sub' onclick='showCharacter(this)' id='dialog-ajax'>" + data.guessCharacter.name + "</a>"
                if (data.flag === 'true') { // 猜测正确
                    victory();
                }
                if (count.innerHTML === '0') {
                    failure();
                }
            } else {
                layer.open({
                    type: 1,
                    offset: 'b',
                    anim: 'slideUp', // 从下往上
                    area: ['420px','48px'],
                    title: false,
                    shadeClose: true,
                    closeBtn: 0,
                    time: 2000,
                    id: 'ID-demo-layer-direction-b',
                    content: '<div class="bg-deep align-center padding-top-small" style="height: 100%">' +
                        '角色不存在，请输入正确的角色名' +
                        '</div>'
                });
            }
        },
        error:function(xhr, status, error) {
            // 请求出错，显示错误提示信息
            $('#register-error-message').text('请求出错：' + error);
        }
    })
    return false;
}

function showCharacter(obj) {
    let name = $.trim(obj.innerHTML);
    console.log(name);
    $.ajax({
        url: '/character/getCharacter',
        method: 'POST',
        data: {name: name},
        success:function(data) {
            if (data != null) {
                layer.open({
                    type: 1,
                    area: ['728px','auto'],
                    title: false,
                    closeBtn: 0,
                    shade: 0.6,
                    shadeClose: true,
                    content:
                        '<h3 class="bg-deep text-white margin-none padding-top-large padding-left-large">角色信息</h3>' +
                        '<div class="padding-small bg-deep align-center size-big padding-bottom-large padding-top-big">' +
                        '<div><strong>姓名： </strong>' + data.name + '</div>' +
                        '<div><strong>稀有度：</strong> ' + data.quality + '</div>' +
                        '<div><strong>武器： </strong>' + data.weapon + '</div>' +
                        '<div><strong>元素： </strong>' + data.vision + '</div>' +
                        '<div><strong>性别： </strong>' + data.gender + '</div>' +
                        '<div><strong>国家： </strong>' + data.region + '</div>' +
                        '</div>'
                })
            }
        }
    })
}

function victory() {
    document.getElementById('guessing').style.display = 'none';
    let end = document.getElementById('guess-end');
    end.style.display = 'block';
    end.children[0].style.display = 'block';
}

function failure() {
    layer.closeLast();
    document.getElementById('guessing').style.display = 'none';
    let end = document.getElementById('guess-end');
    end.style.display = 'block';
    end.children[1].style.display = 'block';
}

function help() {
    layer.open({
        type: 1,
        area: ['728px','auto'],
        title: false,
        closeBtn: 0,
        content:
            '<div class="padding-small bg-deep align-center size-big padding-top-big">' +
            '确定要拿甜甜花酿鸡向派蒙请求帮助吗?<br><br>' +
            '<div class="align-right">' +
            '<button class="button bg-deep size-big" onclick="layer.closeLast()">' +
            '<span class="text-sub">否</span>' +
            '</button>' +
            '<button class="button bg-deep size-big" onclick="failure()">' +
            '<span class="text-sub">是</span>' +
            '</button>' +
            '</div>' +
            '</div>'
    })
}