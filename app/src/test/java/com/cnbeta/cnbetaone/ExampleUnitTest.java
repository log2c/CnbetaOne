package com.cnbeta.cnbetaone;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.di.module.NetworkModule;

import org.junit.Test;

import io.reactivex.functions.Consumer;
import okhttp3.HttpUrl;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testApi() {
        NetworkModule networkModule = new NetworkModule();
        CnbetaApi cnbetaApi = networkModule.provideRetrofit(HttpUrl.parse(Constants.CnbetaUrl.BASE_URL)).create(CnbetaApi.class);
        cnbetaApi.articlesSign().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.print(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.print(throwable);
            }
        });

    }
}