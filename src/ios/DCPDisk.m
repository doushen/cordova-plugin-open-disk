#import "DCPDisk.h"

@implementation DCPDisk
//Description:获取磁盘
- (void)getDiskinfo:(CDVInvokedUrlCommand*)command
{
    NSString* callbackId = [command callbackId];
    NSDictionary* options = [command argumentAtIndex:0 withDefault:nil];
    BOOL isDebug = [options[@"debug"]  isEqualToString:@"true"];

    if(isDebug){
        NSLog(@"获取磁盘...");
        NSLog(@"options: %@", options);
    }

    //当前可用磁盘大小
    long long freeDisk = [self freeDiskSpace];
    //当前磁盘总大小
    long long totalDisk = [self totalDiskSpace];

    NSDictionary *dict = @{
            @"freeDisk":     [NSString stringWithFormat:@"%lli", freeDisk],
            @"totalDisk":    [NSString stringWithFormat:@"%lli", totalDisk]
    };
    CDVPluginResult *result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:@{@"info": dict}];
    [self.commandDelegate sendPluginResult:result callbackId:callbackId];

}

/**
 *  磁盘总大小
 *
 *  @return long long
 */
- (long long)totalDiskSpace
{
    NSDictionary *fattributes = [[NSFileManager defaultManager] attributesOfFileSystemForPath:NSHomeDirectory() error:nil];
    return [[fattributes objectForKey:NSFileSystemSize] longLongValue];
}

/**
 *  剩余磁盘大小
 *
 *  @return long long
 */
- (long long)freeDiskSpace
{
    NSDictionary *fattributes = [[NSFileManager defaultManager] attributesOfFileSystemForPath:NSHomeDirectory() error:nil];
    return [[fattributes objectForKey:NSFileSystemFreeSize] longLongValue];
}
@end
