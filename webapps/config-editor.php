<?php
/**
 * Khanza SIMRS database.xml Editor
 * Added by: RS Ibunda PWJ Klampok
 * Require PHP 7.0 and up
 */

/** ----------------- SET PASSWORD -----------------
 * Jika sudah diinstall, silahkan atur secret/password,
 * misal : 
 *   $secret = 'rahasiaperusahaan';
 **/
$secret = '';

// ----------------- END PASSWORD ----------------- //

if(empty($secret)) die('Secret belum di set, untuk alasan keamanan halaman ini tidak bisa digunakan');

/** Ubah jika lokasi file berbeda */
$config_file = realpath('../setting/database.xml');

$is_login = isset($_COOKIE['secret']) ? $_COOKIE['secret'] == $secret : false;

if( !$is_login 
    && isset($_POST['secret'])
    && ($is_login = ($secret == $_POST['secret'])) )
{
    setcookie('secret', $secret );
}
// Khanza AES KEY
$key = "Bar12345Bar12345"; // 128 bit key
$iv  = "sayangsamakhanza"; // 16 bytes IV
$encryptedKeys = [
'HOST', 'DATABASE', 'PORT', 'USER', 'PAS', 'HOSTHYBRIDWEB', 'SECRETKEYAPIBPJS', 'CONSIDAPIBPJS',
'SECRETKEYAPIAPLICARE', 'CONSIDAPIAPLICARE', 'SECRETKEYAPIPCARE', 'CONSIDAPIPCARE', 'PASSPCARE',
'USERPCARE', 'IDSISRUTE', 'PASSSISRUTE', 'IDSIRS', 'PASSSIRS', 'IDSITT', 'PASSSITT', 'IDCORONA',
'PASSCORONA', 'USERDUKCAPILJAKARTA', 'PASSDUKCAPILJAKARTA', 'USERDUKCAPIL', 'PASSDUKCAPIL',
'KEYWSLICA', 'HOSTFUJI', 'DATABASEFUJI', 'PORTFUJI', 'USERFUJI', 'PASFUJI', 'HOSTSYSMEX', 'DATABASESYSMEX',
'PORTSYSMEX', 'USERSYSMEX', 'PASSYSMEX', 'HOSTELIMS', 'DATABASEELIMS', 'PORTELIMS', 'USERELIMS', 'PASELIMS'
];

function encrypt( $val ){
    global $key, $iv;
    return base64_encode( openssl_encrypt($val,'AES-128-CBC',$key, OPENSSL_RAW_DATA, $iv) );
}

function decrypt( $val ){
    global $key, $iv;
    return openssl_decrypt( base64_decode($val), 'AES-128-CBC', $key, OPENSSL_RAW_DATA , $iv);
}

if( $is_login ){
    $config = simplexml_load_file($config_file);
    if(isset($_POST['HOST'])){
        $diff_count = 0;
        foreach( $_POST as $name => $val ){
            if( in_array($name, $encryptedKeys )){
                $val = encrypt($val);
            }
            $entry = $config->xpath("/properties/entry[@key='$name']");
            if( $entry[0] && $entry[0][0] != $val ){
                $diff_count++;
                $entry[0][0] = $val;
            }
        }
        unset($entry);
    }
}

?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Konfigurasi Khanza</title>
    <style>
        form {
            text-align: center;
            max-width: 300px;
            margin: 20px auto;
        }
        input[type=text]{
            width: 100%;
        }
    </style>
</head>

<body>
    <form method="post">
        <?php if( $is_login ): ?>
        <h2>Konfigurasi Khanza SIMRS</h2>
        <?php if( $diff_count > 0 ){
            $xml = preg_replace('/(<entry .*)\/>/','$1></entry>', $config->asXml());
            if( file_put_contents($config_file, $xml ) ){
                echo '<b>Perubahan disimpan</b>';
            }else{
                echo '<b>GAGAL MENYIMPAN PERUBAHAN. is dir writable?</b>';
            }
        } ?>
        <?php foreach( $config->entry as $entry ){
            $val = in_array($entry['key'], $encryptedKeys ) ? decrypt( $entry[0] ) : $entry[0];

            printf(
                '<label>%1$s</label><br/><input name="%1$s" value="%2$s" type="text" /><br/><br/>',
                htmlentities($entry['key']),
                htmlentities($val)
            );
        }
        ?>
        <?php else : ?>
        <input type="password" name="secret" placeholder="Masukan katakunci" />
        <?php endif ?>
        <button type="submit">Kirim</button>
    </form>
</body>

</html>