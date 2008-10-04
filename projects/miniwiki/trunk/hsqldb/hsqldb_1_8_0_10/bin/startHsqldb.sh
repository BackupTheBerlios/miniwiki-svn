#! /bin/sh

java -classpath ./../lib/hsqldb.jar org.hsqldb.Server -database.0 file:./../data/MiniWiki -dbname.0 MiniWiki -database.1 file:./../data/testing_MiniWiki -dbname.1 testing_MiniWiki



