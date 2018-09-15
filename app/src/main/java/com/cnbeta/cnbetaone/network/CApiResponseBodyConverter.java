package com.cnbeta.cnbetaone.network;

import com.cnbeta.cnbetaone.entity.CnbetaBaseResponse;
import com.cnbeta.cnbetaone.exception.CApiException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

public class CApiResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CApiResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody response) throws IOException {
        if (!Objects.equals(response.contentType(), CApiRequestBodyConverter.MEDIA_TYPE)) {    // 数据错误
            throw new CApiException("Response ContentType not application/json; charset=UTF-8 ", -1);
        }
        String resStr = response.string();
        CnbetaBaseResponse cnbetaBaseResponse = gson.fromJson(resStr, CnbetaBaseResponse.class);
        if (!cnbetaBaseResponse.getStatus().equals("success")) {
            response.close();
            throw new CApiException("Response status : " + cnbetaBaseResponse.getStatus(), -1);
        }
        MediaType contentType = response.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(resStr.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            response.close();
        }
    }
}