# Shell
### 1.for

### 二.param传递参数
<pre>
1.$0,$1,$2----$*，$@

2.getopts
#!/bin/bash
#prama2.sh
while getopts ":a:b:c:" opt
do
    case $opt in
        a)
        echo "参数a的值$OPTARG"
        ;;
        b)
        echo "参数b的值$OPTARG"
        ;;
        c)
        echo "参数c的值$OPTARG"
        ;;
        ?)
        echo "未知参数"
        exit 1;;
    esac
done
</pre>