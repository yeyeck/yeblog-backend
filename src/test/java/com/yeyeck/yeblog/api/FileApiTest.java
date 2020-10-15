package com.yeyeck.yeblog.api;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileApiTest {
//    public static String str = '# C\n```c\n# include<stadio.h>\n\nint main()\n{\n    print("Hello, World!");\n    return 0;\n}\n```\n\n# java\n```java\nclass HelloWorld {\n    public static void main(String[] args){\n        System.out.println("Hello, World");\n    }\n}\n```\n# Python\n```python\ndef sum(a, b):\n  return a + b\nprint("sum of 1+2: ", sum(1, 2))\n``` \n# HTML\n```html\n<div>\n  <a href="www.yeyeck.com">yeblog</a>\n</div>\n<script>\n$("#id").click(function(){\n  consolo.log("Hello, World!")\n})\n</script>\n<style>\n.yeblog {\n  width: 120px;\n  height: 120px;\n  padding: 0 0 0 0;\n}\n</style>\n```';


    @Test
    public void path() {
        Path rootLocation = Paths.get("/tmp/yeblog/image");
        System.out.println(rootLocation);
    }

    @Test
    public void list() {
        List<Integer> list = new ArrayList<>();
        list.removeAll(null);
    }

}
