$(function () {
    $(window).scroll(function () {
        var hT = $('#scroll-to').offset().top,//div内容所在高度
            hH = $('#scroll-to').outerHeight(),//取总高度
            wH = $(window).height(),//可见高度
            wS = $(this).scrollTop();//页面顶部 到 当前div高度
        console.log((hT - wH), wS);//获得的高度-可视区域大小...

        if (wS > (hT + hH - wH)) {
            var findCategoryId = document.getElementsByClassName("title");
            var categoryId;
            debugger;
            for (i = 0; i < findCategoryId.length; i++) {
                if ($(findCategoryId[i]).find("div").attr('class') == "active") {//判断classname是否为active
                    categoryId = $(findCategoryId[i]).find("a").get(0).getAttribute("categoryId")//取categoryId
                    //$(findCategoryId[i]).find("a").attr("categoryId");
                    console.log(categoryId);
                }
            }
            ;
            if (categoryId == 0) {//如果为初始化页面没有categoryId的话，设置categoryId为19（手机）
                categoryId = 19;
            }
            debugger;
//取当前页面的goodsId
            var findGoodsId = document.getElementsByClassName("goodsList");//寻找所有classname为goodslist的div
            var goodsListByGoodsImg;
            var goodsArray = [];
            for (i = 0; i < findGoodsId.length; i++) {//取页面的  goodsId
                goodsListByGoodsImg = $(findGoodsId[i]).find("img").get(0).getAttribute("goodsId");
                goodsArray.push(goodsListByGoodsImg);
            }
            ;

            var data = {
                "categoryId" : categoryId,
                "goodsArray" : goodsArray
            }
            $.ajax({
                type: 'POST',//方法类型
                url: '/recommendAjax',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (result) {
                    if (result.resultCode == 200) {

                    }
                }
            })
        }

    })
})