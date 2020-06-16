package com.TestRestAssuredBarrigaRest.suitTest;


import com.TestRestAssuredBarrigaRest.Core.BaseTest;
import com.TestRestAssuredBarrigaRest.Tests.AccountTest;
import com.TestRestAssuredBarrigaRest.Tests.DoNotAccessWithoutTokenTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
        DoNotAccessWithoutTokenTest.class,
        AccountTest.class
})
public class SuitAllTests extends BaseTest {

}
