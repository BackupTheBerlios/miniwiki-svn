java -classpath ./hsqldb/lib/hsqldb.jar org.hsqldb.Server -database.0 file:./hsqldb/data/site -dbname.0 site &

cd ./tomcat/bin
./catalina.sh run

