cd /usr/java/zookeeper/
for i in "$@";
  do
   echo "【start$i号机>>>>>>】："
   ./bin/zkServer.sh start ./conf/zoo$i.cfg
   ./bin/zkServer.sh status ./conf/zoo$i.cfg
   echo -e "\n"
 done
