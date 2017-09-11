#import <Cordova/CDV.h>

@interface DCPDisk : CDVPlugin

//Description:获取磁盘
- (void) getDiskinfo:(CDVInvokedUrlCommand*)command;

@end