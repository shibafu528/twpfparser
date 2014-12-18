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

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TwiProfileFactoryTest {

    private void fetch(UserAgent userAgent) throws IOException {
        TwiProfile profile = TwiProfileFactory.getTwiProfile("grslf", userAgent);
        assertEquals(profile.getScreenName(), "@grslf");
    }

    @Test
    public void fetchPCChrome() throws IOException {
        fetch(UserAgent.UA_PC_CHROME);
    }

    @Test
    public void fetchPCIE8() throws IOException {
        fetch(UserAgent.UA_PC_IE8);
    }

    @Test
    public void fetchPCIE10() throws IOException {
        fetch(UserAgent.UA_PC_IE10);
    }

    @Test
    public void fetchPCIE11() throws IOException {
        fetch(UserAgent.UA_PC_IE11);
    }

    @Test
    public void fetchAndroid2_3() throws IOException {
        fetch(UserAgent.UA_ANDROID_GB);
    }

    @Test
    public void fetchAndroid4_1() throws IOException {
        fetch(UserAgent.UA_ANDROID_JB);
    }
}