package com.bw.movie.util;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * @author mengxuan
 * @包名 com.bw.movie.util
 * @MengXuanmengxuan
 * @日期2020/5/17
 * @项目名MengMoviebw
 * @类名FrescoUtil
 **/
public class FrescoUtil {
    private static final class PFrescoUtil {
        private static final FrescoUtil FRESCO_UTIL = new FrescoUtil();
    }

    public static FrescoUtil getInstance() {
        return PFrescoUtil.FRESCO_UTIL;
    }

    public void myProgressive(String util, SimpleDraweeView imageView) {
        Uri uri = Uri.parse(util);
        ImageRequest build = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true).build();///渐进式渲染
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(build)
                .build();
        imageView.setController(controller);

    }
    //圆形
    public void myRound(String util, SimpleDraweeView imageView){
        Uri uri = Uri.parse(util);
        RoundingParams params = RoundingParams.asCircle();
        imageView.getHierarchy().setRoundingParams(params);
        imageView.setImageURI(uri);

    }
    //圆角
    public void myFillet(String util, SimpleDraweeView imageView){
        Uri uri = Uri.parse(util);
        RoundingParams params = RoundingParams.fromCornersRadii(30,30,30,30);
        imageView.getHierarchy().setRoundingParams(params);
        imageView.setImageURI(uri);
    }

}
