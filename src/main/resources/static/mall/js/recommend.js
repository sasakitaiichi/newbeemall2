$(function () {
    // // $('.list').on('change', function () {
    //     $.ajax({
    //         url: '/recommend',
    //         success: function (result) {
    //             var categoryList = "";
    //             var goodsCategoryList = result.data.goodsCategoryList;
    //             var length = goodsCategoryList.length;
    //             if (result.resultCode == 200) {
    //                 debugger;
    //                 for (var i = 0; i < length; i++) {
    //                     categoryList = '<li className=\"categoryLIst\" data-category-id=\"' + goodsCategoryList[i].categoryId + '\">'
    //                         + '<a href=\"/recommend?categoryId=' + goodsCategoryList[i].categoryId + '\">'
    //                         + '<div className=\"title-wrap\">'
    //                         + '<em className=\"title\">' + goodsCategoryList[i].categoryName + '</em>'
    //                         + '</div>'
    //                         + '</a>'
    //                         + '</li>'
    //                 }
    //                 ;
    //                 $('.list').find('ul').html(categoryList);
    //             } else {
    //                 swal(result.message, {
    //                     icon: "error",
    //                 })
    //             }
    //         }
    //     });
    // // });

    $('.main').on('change', function () {
        $.ajax({
            url: '/recommend?categoryId=' + categoryId,
            success: function (result) {
                var categoryGoods = "";
                var goodsStore = result.data.goodsStore;
                var length = goodsStore.length;
                if (result.resultCode == 200) {
                    for (var i = 0; i < length; i++) {
                        categoryGoods = '<li className=\"categoryGoods\">'
                            + '<div className=\"items\">'
                            + '<a th:href=\"@{\'/goods/detail/\'+${' + goodsStore[i].goodsId + '}}\">'
                            + '<img th:src=\"@{${' + goodsStore[i].img + '}}\" th:alt=\"${' + goodsStore[i].img + '}\">'
                            + '</a>'
                            + '</div>'
                            + '</li>'
                    }
                    $('.main').find('ul').html(categoryGoods);
                } else {
                    swal(result.message, {
                        icon: "error",
                    })
                }
            }
        })

    })
});