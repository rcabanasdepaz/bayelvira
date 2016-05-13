#!/usr/bin/perl -w

#######################################################################################
#
#	makeElvira for Elvira
#	2003, Luis Daniel Hernandez Molinero
#
#	Initial Goal: 		To make the last makefile from *.java (not gcj)
#	Secundary goals:	To compile the Elvira Project in local hard disk (*.class, .jar and docs)
#	Usage;			./makerElvira option
#	Requirement:		Perl5+, Bash, sdk-java (1.4.1), zip.
#	Tested: 		Linux (Knoppix-debian is OK)
#	Date:			11/6/2003
#	e-mail:			ldaniel@dif.um.es
#
#	NO WARRANTY
#	BECAUSE THE PROGRAM IS LICENSED FREE OF CHARGE, THERE IS NO WARRANTY
#	FOR THE PROGRAM. THE PROGRAM IS "AS IS" WITHOUT WARRANTY OF ANY KIND.
#
#######################################################################################
#

# Variables

$temp_make = ".temp_newMakefile"; # Save every file .java
$temp_class = ".temp_class";	# Save every file .class
$temp_propierties = ".temp_propierties"; # for jar
$temp_images = ".temp_images";	# For jar
$temp_manifest = ".temp_manifest";# For jar
$out_jar = "elvira2.jar";	# For jar
$out_zip = "Elvira2.zip";	# For zip
$newfile = "makefile_new";	# Makefile to make Elvira
$newfiledoc = "makefiledoc_new";# Makefile to run javadoc
$compiler = "javac";		# Compiler java
$flags = "-deprecation";	# Options for the compiler java
$compilerjar = "jar cvfm";	# Compiler jar
$compilerdoc = "javadoc";	# Compiler javadoc
$dirdoc = "./doc";		# Target directory for javadoc
$flagsdoc = "-d $dirdoc -private -version -author  -doctitle \"Docs for Elvira2 Project\" -J-Xmx128m";# Options for javadoc
$first_dir = '\./';		# A filter
$newline = '\n';		# A filter
$vm = "java"; 			# Your java virtual machine
$primaryclass = "elvira/Elvira";# The primary class (for jar)



if ($#ARGV != 0) {
 &usage();
}

# Save the argument
$opt = $ARGV[$#ARGV];

# To do Makefile
if ($opt eq "make") {
  &make();
}

# To do makefile and to compile Elvira (*.class)
if ($opt eq "makeElvira") {
  &makeElvira();
}


# To do makefile, to compile Elvira (*.class) and to run Elvira.class
if ($opt eq "exec") {
  &execElvira();
}

# To clear *.class
if ($opt eq "clearclass") {
  &clearClass();
}

# To run javadoc
if ($opt eq "doc") {
  &doc();
}

# Clear $dirdoc
if ($opt eq "cleardoc") {
  &clearDoc();
}

# Clear $dirdoc
if ($opt eq "jar") {
  &jar();
}


#######################################
###############
sub make {
	# Saving all files .java
	system("find . -name '*.java' -print > $temp_make");

	open (FILEOUT, "> $newfile") || die "Cant make file !!\n";
	print FILEOUT "JAVAC=$compiler\n";
	print FILEOUT "FLAGS_JAVA= $flags\n\n";
	open (FILEIN, "<$temp_make") || die "Cant open file  !!\n";
		print FILEOUT "SOURCES = ";
		while (<FILEIN>) {
			@splta=split(/$first_dir/,$_);
			@spltb=split(/$newline/,$splta[$#splta]);
			$file=$spltb[$#spltb];
			print  FILEOUT "$file ";
		}
	close (FILEIN);
	print FILEOUT "\n\nElvira : \$(SOURCES:java=class)\n\n";
	print FILEOUT "%.class : %.java\n";
	print FILEOUT "\t\$(JAVAC) \$(FLAGS_JAVA) \$<";
	close (FILEOUT);
	system("rm $temp_make");
	print "\n $newfile done !!\n";
}

#######################################
###############
sub makeElvira {
	&clearClass();
	&make();
	system("make -f  $newfile");
	print "done!";
}

#######################################
###############
sub execElvira {
	&makeElvira();
	system("$vm $primaryclass");
}

#######################################
###############
sub clearClass {
	# Saving all files .java
	system("find . -name '*.class' -print > $temp_class");

	open (FILEIN, "<$temp_class") || die "Cant open file  !!\n";
	while (<FILEIN>) {
		$_ =~ s/\$/\\\$/g;
		system("rm -f $_");
	}
	close (FILEIN);
	system("rm $temp_class");
	print "*.class removed !";
}



#######################################
###############
sub clearDoc {
  	system("rm -rf $dirdoc > /dev/null");
}

#######################################
###############
sub doc{
	&clearDoc();
	# Saving every file .java
	system("find . -name '*.java' -print > $temp_make");
	open (FILEOUT, "> $newfiledoc") || die "Cant make file !!\n";
	print FILEOUT "JAVAC=$compilerdoc\n";
	print FILEOUT "FLAGS_JAVA= $flagsdoc\n\n";
	open (FILEIN, "<$temp_make") || die "Cant open file  !!\n";
		print FILEOUT "SOURCES = ";
		while (<FILEIN>) {
			@splta=split(/$first_dir/,$_);
			@spltb=split(/$newline/,$splta[$#splta]);
			$file=$spltb[$#spltb];
			print  FILEOUT "$file ";
		}
	close (FILEIN);
	print FILEOUT "\n\nElvira : \n\n";
	print FILEOUT "\t\$(JAVAC) \$(FLAGS_JAVA) \$(SOURCES)";
	close (FILEOUT);
	system("rm $temp_make; make -f $newfiledoc; rm $newfiledoc");
}


#######################################
###############
sub jar {
	&makeElvira();
	# Saving all files .java
	system("find . -name '*.class' -print > $temp_class");
	system("find . -name '*.properties' -print > $temp_propierties");
	system("find . -name '*.gif' -print > $temp_images");
	system("find . -name '*.jpg' -print >> $temp_images");

	$f = '';
	$g = '';

	open (FILEIN, ">$temp_manifest");
		print FILEIN  "Main-Class: elvira/Elvira\n\n";
	close (FILEIN);
	open (FILEIN, "<$temp_class") || die "Cant open file  !!\n";
	while (<FILEIN>) {
		$_ =~ s/\$/\\\$/g;
		@splta=split(/$first_dir/,$_);
		@spltb=split(/$newline/,$splta[$#splta]);
		$file=$spltb[$#spltb];
		$f .= " $file";
	}
	close (FILEIN);
	open (FILEIN, "<$temp_propierties") || die "Cant open file  !!\n";
	while (<FILEIN>) {
		@splta=split(/$first_dir/,$_);
		@spltb=split(/$newline/,$splta[$#splta]);
		$file=$spltb[$#spltb];
		$f .= " $file";
	}
	close (FILEIN);
	open (FILEIN, "<$temp_images") || die "Cant open file  !!\n";
	while (<FILEIN>) {
		$_ =~ s/\(/\\\(/g;
		$_ =~ s/\)/\\\)/g;
		@splta=split(/$first_dir/,$_);
		@spltb=split(/$newline/,$splta[$#splta]);
		$file=$spltb[$#spltb];
		$g .= " $file";
	}
	close (FILEIN);

	system("rm -f $out_jar $out_zip > /dev/null");
	system("$compilerjar $out_jar $temp_manifest  $f $g");
	system("rm -f $temp_manifest $temp_images $temp_propierties $temp_class > /dev/null");
#	system ("zip $out_zip $out_jar $g");
#	system ("rm -f $out_jar > /dev/null");
}

#######################################
###############
sub usage {
	print "\n----------------------------------------------\n";
	print "makerElvira \t To make the Elvira Tool and misc.";
	print "\n----------------------------------------------\n";
	print "Options:\n";
	print "\tmake\t\tTo make a makefile from *.java ($newfile)\n";
	print "\tmakeElvira\tTo compile Elvira using $newfile\n";
	print "\texec\t\tTo compile and exec. Elvira using $newfile\n";
	print "\tdoc\t\tTo run javadoc (output in the directory $dirdoc)\n";
	print "\tclearclass\tTo remove *.class\n";
	print "\tcleardoc\tTo remove the output of javadoc ($dirdoc)\n";
	print "\tjar\t\tTo make $out_jar (including image files)\n";
	print "\n----------------------------------------\n";
	print "You can change some variables. See the source ;-)";
	print "\n----------------------------------------\n";
	exit;
}

