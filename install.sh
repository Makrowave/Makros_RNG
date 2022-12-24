if !(echo $(which java) | grep '/usr/bin/java'); then 
    echo "dowloading and installing java..."
    echo $(wget https://download.java.net/java/GA/jdk19.0.1/afdd2e245b014143b62ccb916125e3ce/10/GPL/openjdk-19.0.1_linux-aarch64_bin.tar.gz) \
        $(sudo tar -xvf openjdk* -C /usr/local) \
        $(export JAVA_HOME="/usr/local/jdk*") \
        $(export PATH="$JAVA_HOME/bin:$PATH")
    if (echo $(which java) | grep '/usr/bin/java'); then
        echo "java installed successfully"
    else
        echo "java installation failed"
    fi
fi

echo "Creating launch file..." \
    $(touch Makros_RNG) \
    $(echo '$(cd $HOME/.config/Makros_RNG/src && java Main)' >> Makros_RNG) \
    "Launch file created succesfully"


echo "Beggining instalation process..." \
    $(cd ./src && javac Main.java && cd ..) \
    $(mkdir $HOME/.config/Makros_RNG)
    $(cp --parents `find -name \*.class*` $HOME/.config/Makros_RNG/ ) \
    $(chmod +x Makros_RNG) \
    $(cp Makros_RNG $HOME/.config/Makros_RNG/) \
    $(sudo cp Makros_RNG /bin)
echo "Insatlation finished"
