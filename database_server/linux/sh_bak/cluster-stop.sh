cd /usr/java/zookeeper/
for i in "$@";
  do
   echo "【stop$i号机>>>>>>>】："
   ./bin/zkServer.sh stop ./conf/zoo$i.cfg
   ./bin/zkServer.sh status ./conf/zoo$i.cfg
   echo -e "\n"
 done
