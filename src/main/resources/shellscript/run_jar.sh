source /etc/profile
jar_name=template-0.0.1-SNAPSHOT.jar
jar_path=/devtoolruntime/shellscript/remote_jar
log_path=/devtoolruntime/shellscript/logs/date +%y-%m-%d_out.log
#指向自定义jdk
#export JAVA_HOME=/devtoolruntime/javaruntime/jdk17/jdk-17.0.4.1
#export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
#export PATH=$JAVA_HOME/bin:$PATH
echo ""
pid=$(ps -ef | grep ${jar_name} | grep -v grep | awk '{print $2}')
if [ -z $pid ]; then
echo "prepare to run ${jar_name}"
cd $jar_path
nohup java -jar $jar_name >$log_path 2>&1 &
pid=$(ps -ef | grep $jar_name | grep -v grep | awk '{print $2}')
echo "${jar_name} start success pid=${pid}"
else
echo "${jar_name} is running prepare to shutdown service pid=${pid}"
kill -9 $pid
sleep 0.5
echo "${jar_name} has shutdown"
echo ""
echo "prepare to run ${jar_name}"
cd $jar_path
nohup java -jar $jar_name >$log_path 2>&1 &
pid=$(ps -ef | grep $jar_name | grep -v grep | awk '{print $2}')
echo "${jar_name} start success pid=${pid}"
fi
echo ""