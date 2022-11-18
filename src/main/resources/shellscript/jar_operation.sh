jar_name=template-0.0.1-SNAPSHOT.jar
jar_path=/devtoolruntime/shellscript/remote_jar
log_path=/devtoolruntime/shellscript/logs/`date +%y-%m-%d`_out.log
#指向自定义jdk
#export JAVA_HOME=/devtoolruntime/javaruntime/jdk17/jdk-17.0.4.1
#export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
#export PATH=$JAVA_HOME/bin:$PATH

stop() {
  echo ""
  pid=$(ps -ef | grep $jar_name | grep -v grep | awk '{print $2}')
  if [ -z $pid ]; then # -z 是判断是否为空
    echo "${jar_name}未运行！"
  else
    echo "准备关闭进程$pid"
    kill -9 $pid
    sleep 0.5
    echo "${jar_name}已关闭！"
  fi
  echo ""
}


start() {
  echo ""
  pid=$(ps -ef | grep $jar_name | grep -v grep | awk '{print $2}')
  if [ -z $pid ]; then
    cd $jar_path  # 这里转到jar包目录执行命令，是为了使用jar_path下的config文件，貌似java程序只能识别当前执行命令目录下的配置，否则就是使用已打入Jar包的配置文件。
    nohup java -jar $jar_name >$log_path 2>&1 &
    pid=$(ps -ef | grep $jar_name | grep -v grep | awk '{print $2}')
    echo "应用 ${jar_name}启动成功，pid=${pid}"
    # tail -f $log_path

  else
    echo "应用 ${jar_name} 正在运行，pid = ${pid}"
  fi
  echo ""
}


status() {
  echo ""
  pid=$(ps -ef | grep $jar_name | grep -v grep | awk '{print $2}')
  if [ -z $pid ]; then
    echo "应用 ${jar_name} 未运行"
  else
    echo "应用 ${jar_name} 正在运行，pid = ${pid}"
  fi
  echo ""

}


restart() {
  stop

  sleep 2
  echo "停止完成，准备启动jar包"
  start
}

action() {
  echo "请输入数字选择要执行的操作：1=启动，2=重启，3=停止，4=查看运行状态，5=退出"
  echo '你输入的数字为:'
  read a
  case $a in
  "1")
    start
    ;;
  "2")
    restart
    ;;
  "3")
    stop
    ;;
  "4")
    status
    ;;
  "5")
    exit 1
    ;;
  *)
    echo "输入错误，请重新输入"
    action
    ;;
  esac
}
#restart
action
