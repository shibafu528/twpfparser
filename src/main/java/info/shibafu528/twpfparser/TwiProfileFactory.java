/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 shibafu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package info.shibafu528.twpfparser;

import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by shibafu on 14/12/17.
 */
public class TwiProfileFactory {

    private TwiProfileFactory() {}

    /**
     * ツイフィールからプロフィール情報を取得します<br>
     * UserAgentにはPC版Chromeのものを使用します
     * @param screenName 対象ユーザのScreenName(@hoge)
     * @return プロフィール情報
     * @throws IOException 通信エラー時にthrow
     */
    public static TwiProfile getTwiProfile(String screenName) throws IOException {
        return getTwiProfile(screenName, UserAgent.UA_PC_CHROME);
    }

    /**
     * ツイフィールからプロフィール情報を取得します
     * @param screenName 対象ユーザのScreenName(@hoge)
     * @param userAgent 通信に使用するUserAgent
     * @return プロフィール情報
     * @throws IOException 通信エラー時にthrow
     */
    public static TwiProfile getTwiProfile(String screenName, UserAgent userAgent) throws IOException {
        return new TwiProfileImpl(Jsoup.connect("http://twpf.jp/" + screenName)
                .timeout(10000)
                .userAgent(userAgent.getUserAgent())
                .get());
    }
}
