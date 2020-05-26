package com.bw.movie.url;

/**
 * @author
 * @包名 com.bw.movie
 * @MengXuanmengxuan
 * @日期2020/4/17
 * @项目名Movie
 * @类名Api
 **/
public interface Api {
//内 外
    String URL_INSIDE="http://172.17.8.100/";
    String URL_OUTER="http://mobile.bwstudent.com/";

    String REGISTER="movieApi/user/v2/register";
    String SENDOUTEMAILCODE="movieApi/user/v2/sendOutEmailCode";
    String LOGIN="movieApi/user/v2/login";

    String FINDCOMINGSOONMOVIELIST="movieApi/movie/v2/findComingSoonMovieList";
    String FINDRELEASEMOVIELIST="movieApi/movie/v2/findReleaseMovieList";
    String FINDHOTMOVIELIST="movieApi/movie/v2/findHotMovieList";
    String BANNER="movieApi/tool/v2/banner";




}
