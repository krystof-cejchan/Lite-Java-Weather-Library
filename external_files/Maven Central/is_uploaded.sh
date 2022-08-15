yourURL="https://repo1.maven.org/maven2/cz/krystofcejchan/"

if curl --output /dev/null --silent --head --fail "$yourURL"
then
    echo "This URL Exist"
else
    echo "This URL Not Exist"
fi