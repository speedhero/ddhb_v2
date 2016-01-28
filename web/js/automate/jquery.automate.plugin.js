;(function($){
    $.fn.extend({
        "startAutomate":function(option){
        	
            var container = $(this);
            var rotateDiv = $(this).find(".block").first();
            var containerId = $(this).attr("id");
            
            $(container).find(".navigaterDiv").remove();
            $(rotateDiv).animate({ "margin-left": 0 }, "slow" );
            
            var firstItem = $(container).find(".item").first();
            if ($(firstItem).length == 0){
            	return;
            }
            
            var navigaterDiv = $("<div class='navigaterDiv'></div>");
            $(container).append(navigaterDiv);
            var ul = $("<ul></ul>");
            $(navigaterDiv).append(ul);
            var itemWidth = $(firstItem).css("width").toString();
            itemWidth = itemWidth.replace("px", "");
            itemWidth = parseInt(itemWidth);
            
            var itemMarginRight = $(firstItem).css("margin-right").toString();
            itemMarginRight = itemMarginRight.replace("px", "");
            itemMarginRight = parseInt(itemMarginRight);
            
            var itemMarginLeft = $(firstItem).css("margin-left").toString();
            itemMarginLeft = itemMarginLeft.replace("px", "");
            itemMarginLeft = parseInt(itemMarginLeft);
            
            var itemPaddingLeft = $(firstItem).css("padding-left").toString();
            itemPaddingLeft = itemPaddingLeft.replace("px", "");
            itemPaddingLeft = parseInt(itemPaddingLeft);
            
            var pageCapacity = option.pageCapacity;
            var containerWidth = $(container).width();
                        
            
            var totalItem = $(rotateDiv).find(".item").length;
            var totalPage = 0;
            if (totalItem % pageCapacity == 0){
                totalPage = totalItem / pageCapacity;
            }else{
                totalPage = Math.floor(totalItem / pageCapacity) + 1;
            }
            var currentPage = 1;
            var ulWidth = 95;
            var liStr = "";
            liStr += "<li><a class='goLeft'></a></li>";
            for (var i = 0; i < totalPage; i++){
                if (i == 0){
                    liStr += "<li><a class='dian selectedDian' id='" + $(container).attr("id") + "_dian_" + (i + 1) + "'></a></li>";
                }else{
                    liStr += "<li><a class='dian unselectedDian' id='" + $(container).attr("id") + "_dian_" + (i + 1) + "'></a></li>";
                } 
                //ulWidth += 23;
                ulWidth += 40;
            }
            var urlWidthStr = ulWidth + "px";
            liStr += "<li><a class='goRight'></a></li>";
            $(ul).append(liStr);   
            $(ul).css("width", urlWidthStr);
            $("#" + containerId + " .goLeft").click(function(){
                toLeft();
            });
            
            function toLeft(){
                if (currentPage == 1){
                    return;
                }
                
                $("#" + containerId + "_dian_" + currentPage).removeClass("selectedDian");
                $("#" + containerId + "_dian_" + currentPage).addClass("unselectedDian");
                currentPage = currentPage - 1;
                $("#" + containerId + "_dian_" + currentPage).removeClass("unselectedDian");
                $("#" + containerId + "_dian_" + currentPage).addClass("selectedDian");
                
                var left = "+=" + ((itemWidth + itemMarginRight + itemMarginLeft + itemPaddingLeft * 2) * pageCapacity) + "px";
                
                $(rotateDiv).animate({ "margin-left": left }, "slow" );
            }
            
            $("#" + containerId + " .goRight").click(function(){
                goRight();
            });
            
            function goRight(){
                if (currentPage == totalPage){
                    return;
                }
                
                var left = "-=" + ((itemWidth + itemMarginRight + itemMarginLeft + itemPaddingLeft * 2) * pageCapacity) + "px";
                
                $("#" + containerId + "_dian_" + currentPage).removeClass("selectedDian");
                $("#" + containerId + "_dian_" + currentPage).addClass("unselectedDian");
                currentPage = currentPage + 1;
                $("#" + containerId + "_dian_" + currentPage).removeClass("unselectedDian");
                $("#" + containerId + "_dian_" + currentPage).addClass("selectedDian");
                
                $(rotateDiv).animate({ "margin-left": left }, "slow" );
            }
            
            $("#" + containerId + " .dian").click(function(){
                var dianIdStr = $(this).attr("id");
                var idPrefix = $(container).attr("id") + "_dian_";
                idPrefix = idPrefix.toString();
                var dianId = dianIdStr.replace(idPrefix, "");
                dianId = parseInt(dianId);
                if (dianId == currentPage){
                    return;
                }
                
                var pageDistance = currentPage - dianId;
                
                var previousDian = $("#" + containerId + " .selectedDian").first();
                $(previousDian).removeClass("selectedDian");
                $(previousDian).addClass("unselectedDian");
                $("#" + dianIdStr).removeClass("unselectedDian");
                $("#" + dianIdStr).addClass("selectedDian");
                
                     
                var left = 0;
                if (pageDistance > 0){
                    left = "+=" + ((itemWidth + itemMarginRight + itemMarginLeft + itemPaddingLeft * 2) * pageCapacity * pageDistance) + "px"; 
                }else{
                    left = "-=" + ((itemWidth + itemMarginRight + itemMarginLeft + itemPaddingLeft * 2) * pageCapacity * pageDistance * -1) + "px"; 
                }
                
                $(rotateDiv).animate({ "margin-left": left }, "slow" );
                currentPage = dianId;
            });
        }
    });
})(jQuery);