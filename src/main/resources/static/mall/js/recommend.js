$(function() {
    $(window).scroll(function() {
        var hT = $('#scroll-to').offset().top,//div内容所在高度
            hH = $('#scroll-to').outerHeight(),//取总高度
            wH = $(window).height(),//可见高度
            wS = $(this).scrollTop();//页面顶部 到 当前div高度
        console.log((hT - wH), wS);//获得的高度-可视区域大小...

        if (wS > (hT+hH-wH)){
            var findCategoryId = document.getElementsByClassName("title");
            var goodsListByCategoryId;
            debugger;
            for (i = 0; i < findCategoryId.length; i++) {
                if($(findCategoryId[i]).find("div").attr('class')=="active"){//判断classname是否为active
                    goodsListByCategoryId = $(findCategoryId[i]).find("a").get(0).getAttribute("categoryId")//取categoryId
                    //$(findCategoryId[i]).find("a").attr("categoryId");
                    console.log(goodsListByCategoryId);
                }
            };
            if(goodsListByCategoryId == 0 ){//如果为初始化页面没有categoryId的话，设置categoryId为19（手机）
                goodsListByCategoryId = 19;
            }
//取当前页面的goodsId
            var findGoodsId = document.getElementsByClassName("goodslist");//寻找所有classname为goodslist的div
            var goodsListByGoodsId;
            for (i = 0; i < findGoodsId.length; i++) {//取页面的  goodsId
                goodsListByGoodsId = $(findGoodsId[i]).find("img").get(0).getAttribute("goodsId");
            };
        }

    })


})