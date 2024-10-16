package seungkyu.mockito.test;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GreetingGenerator {

    private final String who;

    public String execute(){
        return "hello " + who;
    }
}
