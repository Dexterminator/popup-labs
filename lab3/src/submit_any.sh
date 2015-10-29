camel=$1
lower=$(echo "$1" | tr '[:upper:]' '[:lower:]')
cmd="kattis.py -f -p $lower -m se.dxtr.$lower.$camel se/dxtr/$lower/*.java se/dxtr/stringlibrary/*.java"
$cmd
